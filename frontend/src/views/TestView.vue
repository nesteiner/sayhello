<template>
  <div class="test-view">
    <Tabbar active-name="hello">
      <TabbarItem name="hello">
        <h1> Hello World</h1>
      </TabbarItem>

      <TabbarItem name="fuck">
        <h1> Fuck You </h1>
      </TabbarItem>
    </Tabbar>
  </div>
</template>

<script lang="ts" setup>
import {Tabbar, TabbarItem} from "@/components/tabbar";

</script>

<style scoped>

</style>