import React, {useState} from 'react';
import {Button, Text, View} from 'react-native';

const MyCountComponent = ({name}) => {
  const [count, setCount] = useState(0);
  console.log('MyCountComponent 렌더링된다' + name);
  return (
      <>
    <View>
      <Text style={{fontSize: 20}}>count = {count}</Text>
      <View style={{flexDirection:"row", gap:10 }}>
        <Button title={'증가'} onPress={() => setCount(count + 1)} />
        <Button title={'감소'} onPress={() => setCount(count - 1)} />
      </View>
    </View>
      </>
  );
};

export default MyCountComponent;
