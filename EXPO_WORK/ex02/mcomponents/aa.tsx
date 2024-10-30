import axios from "axios";
import { useState } from "react";
import { Text, View, StyleSheet, TouchableOpacity } from "react-native";

const AA = () => {
  const [data, setData] = useState('');
  const changeData = async() => {
    const res = await axios.get('http://112.222.157.156:20057/test/aa')
    setData('변경된 값'+res.data);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.text}>AA</Text>
      <Text style={styles.text}>{data}</Text>

      <TouchableOpacity onPress={changeData}>
        <Text style={styles.text}>♥Press me♥</Text>
      </TouchableOpacity>
    </View>
  );
};

export default AA;

const styles = StyleSheet.create({
  container: {
    backgroundColor: "pink",
    alignItems: "center",
    padding: 20,
    margin: 25,
    justifyContent: "center",
  },
  text: {
    fontSize: 40,
    fontFamily: "GangWonL",
  },
});
