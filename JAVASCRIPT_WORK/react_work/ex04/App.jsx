import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createDrawerNavigator} from '@react-navigation/drawer';
import HomeScreen from './src/screens/HomeScreen';
import Toast from 'react-native-toast-message';
import TodoMainScreen from "./src/screens/TodoMainScreen";
import ProfileScreen from "./src/screens/ProfileScreen";
import ImageUploadScreen from "./src/screens/ImageUploadScreen";

const Drawer = createDrawerNavigator();



export default function App() {
  return (
    <>
      <NavigationContainer>
        <Drawer.Navigator initialRouteName="Home">
          <Drawer.Screen name="Home" component={HomeScreen} />
          <Drawer.Screen name="Profile" component={ProfileScreen} />
          <Drawer.Screen name="TodoMain" component={TodoMainScreen} />
          <Drawer.Screen name="ImageUpload" component={ImageUploadScreen} />
        </Drawer.Navigator>0
      </NavigationContainer>
      <Toast config={{zIndex: 10000}} />
    </>
  );
}

