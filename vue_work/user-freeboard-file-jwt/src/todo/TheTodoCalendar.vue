<template>
    <div class="calendar">
      <div class="header">
        <button @click="prevMonth">◀</button>
        <h2>{{ monthYear }}</h2>
        <button @click="nextMonth">▶</button>
      </div>
      <div class="days">
        <div class="day" v-for="day in days" :key="day">{{ day }}</div>
        <div class="date" v-for="date in dates" :key="date" @click="selectDate(date)">
          {{ date }}
          <div class="todos">
            <div v-for="todo in getTodosForDate(date)" :key="todo">{{ todo }}</div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        currentDate: new Date(),
        todos: {}
      };
    },
    computed: {
      monthYear() {
        return `${this.currentDate.toLocaleString('default', { month: 'long' })} ${this.currentDate.getFullYear()}`;
      },
      days() {
        return ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
      },
      dates() {
        const dates = [];
        const firstDay = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth(), 1);
        const lastDay = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth() + 1, 0);
        
        // 이전 달의 마지막 몇 일 추가
        for (let i = firstDay.getDay(); i > 0; i--) {
          dates.push('');
        }
  
        // 현재 달 날짜 추가
        for (let i = 1; i <= lastDay.getDate(); i++) {
          dates.push(i);
        }
  
        return dates;
      }
    },
    methods: {
      prevMonth() {
        this.currentDate.setMonth(this.currentDate.getMonth() - 1);
      },
      nextMonth() {
        this.currentDate.setMonth(this.currentDate.getMonth() + 1);
      },
      selectDate(date) {
        const fullDate = `${this.currentDate.getFullYear()}-${this.currentDate.getMonth() + 1}-${date}`;
        const todo = prompt('Enter your todo:');
        if (todo) {
          if (!this.todos[fullDate]) {
            this.todos[fullDate] = [];
          }
          this.todos[fullDate].push(todo);
        }
      },
      getTodosForDate(date) {
        const fullDate = `${this.currentDate.getFullYear()}-${this.currentDate.getMonth() + 1}-${date}`;
        return this.todos[fullDate] || [];
      }
    }
  };
  </script>
  
  <style scoped>
  .calendar {
    width: 300px;
  }
  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .days, .dates {
    display: grid;
    grid-template-columns: repeat(7, 1fr);
  }
  .day, .date {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: center;
  }
  .date {
    cursor: pointer;
  }
  .todos {
    font-size: 0.8em;
  }
  </style>
  