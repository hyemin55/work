import axios from 'axios';
import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  Button,
  TextInput,
  TouchableOpacity,
  Alert,
} from 'react-native';
import Toast from 'react-native-toast-message';

const HomeScreen = ({ navigation }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    try {
      const res = await axios.post(
          'http://112.222.157.156:3333/login',
          { email, password }
      );
      console.log(res.data); // API 응답 확인
      Toast.show({
        type: 'success',
        text1: '로그인성공',
        text2: '로그인되셨습니다.',
        position: 'top',
      });
      navigation.navigate('TodoMain');
    } catch (error) {
      console.error(error);
      Alert.alert(
          '로그인중에 문제가 발생했습니다.',
          '에러',
          [
            {
              text: '닫기',
              onPress: () => console.log('에러 닫기'),
            },
          ],
          { cancelable: true }
      );
    }
  };

  return (
      <View style={styles.container}>
        <Button
            title="Go to Profile"
            onPress={() => navigation.navigate('Profile')}
        />
        <Text style={{ fontSize: 30, fontWeight: '700', marginVertical: 10 }}>
          로그인
        </Text>
        <View style={styles.inputContainer}>
          <TextInput
              placeholder="이메일"
              value={email}
              onChangeText={setEmail}
              style={styles.input}
          />
          <TextInput
              placeholder="비밀번호"
              value={password}
              onChangeText={setPassword}
              style={styles.input}
              secureTextEntry
          />
          <TouchableOpacity style={styles.buttonContainer} onPress={handleLogin}>
            <Text style={styles.button}>로그인</Text>
          </TouchableOpacity>
        </View>
      </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#e7e7e7',
  },
  inputContainer: {
    width: '70%',
    marginTop: 15,
  },
  input: {
    backgroundColor: '#f9f9f9',
    paddingHorizontal: 15,
    paddingVertical: 10,
    borderRadius: 10,
    marginTop: 5,
  },
  buttonContainer: {
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 20,
  },
  button: {
    backgroundColor: '#fff',
    width: '70%',
    paddingHorizontal: 15,
    paddingVertical: 10,
    borderRadius: 10,
    borderWidth: 3,
    fontSize: 16,
    fontWeight: '500',
    textAlign: 'center',
  },
});

export default HomeScreen;
