import './App.css';
import {useEffect, useState} from "react";
import axios from 'axios';
function App() {
    const [result, setResult] = useState([]);

    useEffect(()=>{

        axios.get('/hello')
            .then(r => setResult(r.data.data))

    }, [])

  return (
    <div>
        Result: {result.map(e=><div>{e}</div>)}
    </div>
  );
}

export default App;
