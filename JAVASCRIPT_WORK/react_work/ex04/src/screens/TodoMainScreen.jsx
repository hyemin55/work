import React from 'react';
import {StyleSheet, Text, View} from 'react-native';
import InputFormComponent from '../components/InputFormComponent';
import Svg, {Circle, Rect} from 'react-native-svg';
import CheckBox from '../assets/images/checkbox-check-svgrepo-com.svg';
import CheckedBox from '../assets/images/checkbox-svgrepo-com.svg';

const todos = [
  {
    id: 1,
    text: '할일1',
    state: 'todo',
  },
  {
    id: 2,
    text: '할일2',
    state: 'todo',
  },
  {
    id: 3,
    text: '할일3',
    state: 'done',
  },
  {
    id: 4,
    text: '할일4',
    state: 'done',
  },
  {
    id: 5,
    text: '할일5',
    state: 'todo',
  },
];
const TodoMainScreen = () => {
  return (
    <>
      <View style={styles.mainContainer}>
        <Text style={styles.pageTitle}>Todo App</Text>
        <View style={styles.listView}>
          <Text style={styles.listTitle}>🖤할일🖤</Text>
          <View style={styles.listContent}>
            {todos.map(todo => {
              if (todo.state === 'todo')
                return (
                  <>
                    <View>
                      <Text>
                        {todo.id}. {todo.text}
                      </Text>
                      <CheckBox width={30} height={30}></CheckBox>
                      {/*<Svg width={100} height={100}>*/}
                      {/*  <Circle*/}
                      {/*    cx="50"*/}
                      {/*    cy="50"*/}
                      {/*    r="40"*/}
                      {/*    stroke="blue"*/}
                      {/*    strokeWidth="2.5"*/}
                      {/*    fill="green"*/}
                      {/*  />*/}
                      {/*  <Rect*/}
                      {/*    x="10"*/}
                      {/*    y="10"*/}
                      {/*    width="30"*/}
                      {/*    height="30"*/}
                      {/*    stroke="red"*/}
                      {/*    strokeWidth="2"*/}
                      {/*    fill="yellow"*/}
                      {/*  />*/}
                      {/*</Svg>*/}
                    </View>
                  </>
                );
            })}
          </View>
        </View>
        <View style={styles.separator}></View>
        <View style={styles.listView}>
          <Text style={styles.listTitle}>🖤완료된일🖤</Text>
          <View style={styles.listContent}>
            {todos.map(todo => {
              if (todo.state === 'done')
                return (
                  <>
                    <View>
                      <Text>
                        {todo.id}. {todo.text}
                      </Text>
                    </View>
                  </>
                );
            })}
          </View>
        </View>
      </View>
      <InputFormComponent />
    </>
  );
};

export default TodoMainScreen;

const styles = StyleSheet.create({
  mainContainer: {
    flex: 1,
    padding: 20,
  },
  pageTitle: {
    fontSize: 30,
    fontWeight: 'bold',
  },
  listView: {
    padding: 15,
  },
  listTitle: {
    fontSize: 20,
    paddingVertical: 5,
  },
  listContent: {
    backgroundColor: '#fff',
    padding: 5,
    borderRadius: 10,
    shadowColor: 'rgb(0,0,0)',
    shadowOffset:{width:5, height:5,},
    shadowOpacity:1,
    shadowRadius:5,
    elevation:5,
  },
  separator: {
    height: 1,
    backgroundColor: 'gray',
  },
});

// export default ProfileScreen;
