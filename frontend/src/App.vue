<template>
  <nav>
    <div>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
    <Dropdown>
      <DropdownItem @click="handleLogout">logout</DropdownItem>
    </Dropdown>
  </nav>
  <router-view class="router-view"/>
</template>

<script lang="ts" setup>
import {Dropdown, DropdownItem} from "@/components/dropdown";
import {useRouter} from "vue-router";
import {logout} from "@/api";

const router = useRouter();

function handleLogout() {
  logout();
  router.push({name: "login"});
}
</script>
<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;

}

nav {
  padding: 30px;
  display: flex;
  justify-content: space-between;
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  background: white;
  border-bottom: 1px solid rgba(0, 0, 0, 0.12);
  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}

.router-view {
  margin-top: 100px;
}
</style>

