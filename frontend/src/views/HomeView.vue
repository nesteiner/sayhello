<template>
  <div class="home">
    <h1>
      SayHello
      <small>to the world</small>
    </h1>

    <div class="message-input">
      <textarea v-model="text"/>
      <button @click="handleSend">send</button>
    </div>

    <Tabbar active-name="all">
      <TabbarItem name="all">
        <template v-for="(message, index) in allMessages" :key="index">
          <Message :id="message.id"
                   :userid="message.userid"
                   :username="message.username"
                   :body="message.body"
                   :timestamp="message.timestamp"/>
        </template>
      </TabbarItem>

      <TabbarItem name="mine">
        <template v-for="(message, index) in myMessages" :key="index">
          <Message :id="message.id"
                   :userid="message.userid"
                   :username="message.username"
                   :body="message.body"
                   :timestamp="message.timestamp"/>
        </template>
      </TabbarItem>
    </Tabbar>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {findAllMessages, findMatchedMessages, LOCAL_TOKEN_KEY, sendMessage} from "@/api";
import {useRouter} from "vue-router";
import {Tabbar, TabbarItem} from "@/components/tabbar";
import Message from "@/components/Message.vue";

const router = useRouter();

const allMessages = ref<Message[]>([])
const myMessages = ref<Message[]>([])
const text = ref("")

async function handleSend() {
  try {
    let response = await sendMessage(text.value);
    allMessages.value.push(response);
    myMessages.value.push(response);
  } catch (error: any) {
    alert(error.response.data.message)
  } finally {
    text.value = ""
  }
}

onMounted(async () => {
  if(localStorage.getItem(LOCAL_TOKEN_KEY) == null) {
    router.replace({name: "login"})
    return;
  }

  try {
    allMessages.value = await findAllMessages();
    myMessages.value = await findMatchedMessages();
  } catch (error: any) {
    console.log(error.response.data);
    alert(error.response.data.message)
    router.replace({name: "login"})
  }
})
</script>