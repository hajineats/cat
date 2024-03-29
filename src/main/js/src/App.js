import TestScreen from "./pages/TestScreen";
import LoginScreen from "./pages/LoginScreen";
import {AppContextProvider} from "./contexts/AppContext";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import InstructionScreen from "./pages/InstructionScreen";
import EndScreen from "./pages/EndScreen";

function App() {

    return (
        <BrowserRouter>
            <AppContextProvider>
                <Routes>
                    <Route path={"/"}  element={<LoginScreen />} />
                    <Route path={"/instruction"}  element={<InstructionScreen />} />
                    <Route path={"/test"} element={<TestScreen />} />
                    <Route path={"/end"} element={<EndScreen />} />
                    <Route path={"*"} element={<LoginScreen/>}/>
                </Routes>
            </AppContextProvider>
        </BrowserRouter>
    );
};

export default App;
