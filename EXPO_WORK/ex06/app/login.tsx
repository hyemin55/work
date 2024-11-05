import { StatusBar } from "expo-status-bar";
import {
  Text,
  View,
  TouchableOpacity,
  TextInput,
  Pressable,
  Alert,
} from "react-native";

export default function App() {
  const doSignUp = () => {
    Alert.alert("Sing up", "회원가입완료");
  };

  return (
    <View
      style={{
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "#fff",
      }}
    >
      <StatusBar style="auto" />
      <Text
        style={{
          textAlign: "center",
          marginTop: 12,
          fontSize: 24,
          fontWeight: "300",
          color: "#fbd38d",
          fontFamily:"NotoSansBlack"
        }}
      >
        Welcome
      </Text>

      {/* Additional components goes here */}
      <View style={{ marginTop: 20, marginHorizontal: 20 }}>
        <View>
          <Text style={{ color: "#a0aec0",fontFamily:"NotoSansBlack" }}>EMAIL:</Text>
          <TextInput
            placeholder="Enter Email..."
            style={{
              borderWidth: 1,
              borderStyle: "dotted",
              padding: 8,
              color: "#718096",
              borderColor: "#f6ad55",
              marginTop: 4,
              fontFamily:"NotoSansBlack"
            }}
          />
        </View>
        <View style={{ marginTop: 12 }}>
          <Text style={{ color: "#a0aec0",fontFamily:"NotoSansBlack" }}>PASSWORD:</Text>
          <TextInput
            secureTextEntry
            placeholder="Enter Password..."
            style={{
              borderWidth: 1,
              borderStyle: "dotted",
              padding: 8,
              color: "#718096",
              borderColor: "#f6ad55",
              marginTop: 4,
              fontFamily:"NotoSansBlack"
            }}
          />
        </View>

        <TouchableOpacity
          style={{
            backgroundColor: "#fbd38d",
            padding: 12,
            marginTop: 16,
          }}
        >
          <Text style={{ textAlign: "center", fontSize: 16, color: "#fff",fontFamily:"NotoSansBlack" }}>
            Login
          </Text>
        </TouchableOpacity>

        <Text
          style={{
            textAlign: "center",
            fontWeight: "400",
            color: "#a0aec0",
            fontSize: 16,
            marginTop: 12,
          }}
        >
          OR
        </Text>
        <View
          style={{
            backgroundColor: "#64748b",
            padding: 12,
            marginVertical: 20,
          }}
        >
          <TouchableOpacity>
            <Text style={{ textAlign: "center", fontSize: 16, color: "#fff",
              fontFamily:"NotoSansBlack"
             }}>
              SignUp
            </Text>
          </TouchableOpacity>
        </View>

        <View style={{ marginTop: 16 }}>
          <TouchableOpacity
            style={{
              flexDirection: "row",
              alignItems: "center",
              justifyContent: "center",
              padding: 8,
              backgroundColor: "#fbd38d",
            }}
          >
            <Text style={{ color: "#fff", marginHorizontal: 8, fontSize: 14,fontFamily:"NotoSansBlack"}}>
              Sign In With Google
            </Text>
          </TouchableOpacity>
        </View>

        <View
          style={{
            marginTop: 24,
            flexDirection: "row",
            justifyContent: "center",
          }}
        >
          <Text>New to FreeCodeCamp? </Text>
          <TouchableOpacity>
            <Text style={{ color: "#f6ad55" }}>Create an Account</Text>
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
}
