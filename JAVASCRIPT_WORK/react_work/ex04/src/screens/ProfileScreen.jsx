import React from 'react';
import {Button, StyleSheet, Text, View} from 'react-native';

const ProfileScreen = ({navigation}) => {
  return (
    <View style={styles.container}>
      <Text style={styles.text}>Profile Screen</Text>
      <Button title="Go to Home" onPress={() => navigation.navigate('Home')} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  text: {
    fontSize: 20,
    fontWeight: 'bold',
  },
});
export default ProfileScreen;
