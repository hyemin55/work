import {Text, View} from "react-native";
import * as React from "react";
import {Button} from "@react-navigation/elements";

function ProfileScreen({navigation, route}) {
  return (
    <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
      <Text>Profile Screen</Text>
      <Text>name ={route.params.name}</Text>
      <Text>age ={route.params.age}</Text>
      <Button
        // push navigate 쌓이는거
        // goBack 뒤로 돌아가는거
        // reset 초기화 몇번 인덱스로 가겠다
        onPress={() => navigation.goBack()}>
        Home
      </Button>
    </View>
  );
}

export default ProfileScreen;
