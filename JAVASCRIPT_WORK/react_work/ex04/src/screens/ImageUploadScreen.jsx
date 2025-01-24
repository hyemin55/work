import {Image, Text, TouchableOpacity, View} from 'react-native';
import MyCountComponent from '../components/MyCountComponent';
import ImageUploadComponent from '../components/ImageUploadComponent';

const ImageUploadScreen = () => {
  console.log('ImageUpload 렌더링된다');
  return (
    <View
      style={{
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        gap: 10,
        backgroundColor: 'rgb(234,234,234)',
      }}>
      <Text style={{fontSize: 30}}>ImageUploadScreen</Text>
      <View style={{flexDirection:"row", gap:10}}>
        <ImageUploadComponent aa={10} />
        <ImageUploadComponent aa={20}/>
        <ImageUploadComponent aa={30}/>
      </View>
      <MyCountComponent name={1} />
      <MyCountComponent name={2} />
      <MyCountComponent name={3} />
    </View>
  );
};

export default ImageUploadScreen;
