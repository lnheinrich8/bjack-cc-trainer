import { useState } from 'react'
import { getHealth } from './api/client'

function App() {
    const [health, setHealth] = useState(null);

    const healthCheck = () => {
        getHealth()
            .then((data) => setHealth(data.status))
            .catch((err) => setHealth("error: " + err.message));
    };

    return (
        <>
            <button onClick={healthCheck}>health</button>
            <p>{health}</p>
        </>
    )
}

export default App
