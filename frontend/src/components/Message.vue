<template>
  <div class="message">
    <div class="header">
      <h5>{{username}}</h5>
      <small>{{timestamp}}</small>
    </div>

    <p>{{body}}</p>
  </div>
</template>

<script lang="ts" setup>
defineProps<{
  id?: number,
  userid: number,
  username: string,
  body: string,
  timestamp: string
}>()
</script>

<style lang="scss" scoped>
div.message {
  padding: .75rem 1.25rem;
  background: white;
  border: 1px solid rgba(0, 0, 0, 0.125);

  div.header {
    display: flex;
    justify-content: space-between;
  }
}
</style>