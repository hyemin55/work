import React from 'react';
import {StyleSheet, Text, TextInput, TouchableOpacity, View} from 'react-native';

const InputFormComponent = () => {
  return (
    <View style={styles.inputContainer}>
      <TextInput style={styles.textInput} placeholder={'할일을 등록하세요'} />
      <TouchableOpacity style={styles.addButton}>
        <Text style={styles.addButtonText}>+</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  textInput: {
    flex: 1,
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    padding: 10,
  },
  inputContainer: {
    padding: 20,
    flexDirection: 'row',
    gap: 10,
  },
  addButton:{
    backgroundColor: 'skyblue',
    justifyContent: 'center',
    alignItems: 'center',
    width: '10%',
    borderRadius: 3,
    marginRight: 5,
  },
  addButtonText: {
    color: 'white',
    fontSize: 20,
  }
});
export default InputFormComponent;
