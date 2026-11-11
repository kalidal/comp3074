import { StyleSheet, Text, View } from 'react-native';

// You can import supported modules from npm
import { Card } from 'react-native-paper';

// or any files within the Snack
import AssetExample from './components/AssetExample';

export default function App() {
  return (
    <View style={styles.container}>

      <View style={styles.card}>

        <Text style={styles.name}>
          Name: Kalid Ali
        </Text>

        <Text style={styles.studentId}>
          Student ID: 101577665
        </Text>

      </View>

        <View style={styles.card2}>

        <Text style={styles.school}>
          George Brown Polytechnic 
        </Text>

        <Text style={styles.course}>
          COMP3074: Mobile App Development
        </Text>

      </View>

    </View>
  );



  
}

const styles = StyleSheet.create({
  container: {
   flex: 1,
  justifyContent: 'center',
  alignItems: 'center',
  padding: 20,
  backgroundColor: 'lightblue'
  },
  name: {
    margin: 24,
    fontSize: 33,
    fontWeight: 'bold',
    textAlign: 'center',
    color: '#1D3557'
  },
  studentId: {
    margin: 24,
    fontSize: 20,
    fontWeight: 'bold',
    textAlign: 'center',
    color: 'purple',
    marginTop: 15,
  },
  school: {
    margin: 24,
    fontSize: 33,
    fontWeight: 'bold',
    textAlign: 'center',
    color: '#1D3557'
  },
  course: {
    margin: 24,
    fontSize: 20,
    fontWeight: 'bold',
    textAlign: 'center',
    color: 'purple',
    marginTop: 15,
  },
  card: {
  width: '85%',
  padding: 25,
  backgroundColor: 'lightgreen',
  borderRadius: 80,
  alignItems: 'center',
  },

card2: {
  width: '85%',
  padding: 25,
  backgroundColor: 'lightyellow',
  borderRadius: 80,
  alignItems: 'center',
  marginTop: 50,
  
},

});
