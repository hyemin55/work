import { StatusBar } from "expo-status-bar";
import {
  StyleSheet,
  Text,
  View,
  Pressable,
  TouchableOpacity,
  Alert,
} from "react-native";
import { Button } from "react-native-web";

export default function App() {
  const doPress1 = () => {
    Alert.alert("doPress1");
    console.log("doPress1");
  };
  const doPress2 = () => {
    Alert.alert("doPress2");
    console.log("doPress2");
  };
  return (
    <View style={styles.container}>
      <Text style={styles.text}>
        Open up App.js to start working on your app!
      </Text>
      <TouchableOpacity style={styles.button} onPress={doPress1}>
        <Text>누르는 버튼</Text>
      </TouchableOpacity>

      <Pressable style={styles.button} onPress={doPress2}>
        <Text style={styles.text}>누르는 버튼</Text>
      </Pressable>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  text: {
    fontSize: 24,
    color: "orange",
  },
  button: {
    backgroundColor: "green",
    padding: 10,
    color: "#fff",
    marginTop: 10,
    borderRadius: 10,
  },
});
