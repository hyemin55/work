import AA from "@/mcomponents/aa";
import { useRouter } from "expo-router";
import { useEffect } from "react";
import { StyleSheet, Text, TouchableOpacity, View } from "react-native";

const Index = () => {
  const router = useRouter();


  const onPress = () => {
    router.push("/(tabs)");
  };

  const onConsole = () => {
    console.log('This is console log.')
  }
  const onMyMain = () =>{
    router.push("/mymain")
  }
  // 최초에 한번만 실행된다.
  useEffect(()=>{
    console.log("Index component useEffect")
  },[])
  return (
    <View style={ss.container}>
      <Text >Index RouterLink - router.push Link - router.push</Text>
      <View style={ss.br} />
      <TouchableOpacity onPress={onPress}>
        <Text style={ss.text}>♥Click Me♥</Text>
        <Text style={ss.text2}>누르면 변경되요.</Text>
      </TouchableOpacity>
      <View style={ss.br} /> 
      <TouchableOpacity onPress={onConsole} >
        <Text style={ss.text}>♥Click Me♥</Text>
        <Text style={ss.text2}>누르면 출력되요.</Text>
      </TouchableOpacity>
      <View style={ss.br} /> 
      <TouchableOpacity onPress={onMyMain} >
        <Text style={ss.text}>♥Click Me♥</Text>
        <Text style={ss.text2}>누르면 MyMainPage로 가요.</Text>
      </TouchableOpacity>
      <AA></AA>
    </View>
  );
};

export default Index;

const ss = StyleSheet.create({
  container: {
    margin: 50,
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  text:{
    fontFamily:'GangWonB',
    fontSize: 30,
    color:'orange',
    
  },  
  text2:{
    fontFamily:'GangWonL',
    fontSize: 20,
    color:'brown',
  
  },
  br:{
    height: 20,
  }
});
