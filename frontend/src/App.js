import 'leaflet/dist/leaflet.css';
import './App.css';
import MapView from './components/MapView';
import GlobalState from "./context/GlobalState";

function App() {
  return (
    <div className="App">
        <GlobalState>
            <MapView/>
        </GlobalState>
    </div>
  );
}

export default App;
