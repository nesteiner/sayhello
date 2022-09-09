<template>
  <div class="tabbar-item" v-show="name === activeName">
    <slot/>
  </div>
</template>

<script lang="ts" setup>
import { inject, onMounted, Ref } from "vue"
const props = defineProps({
  name: {type: String, required: true}
})
const activeName = inject<Ref<string>>('activeName')!;
const names = inject<Ref<Array<string>>>('names')!;

onMounted(() => {
  names.value!.push(props.name!)
})
</script>