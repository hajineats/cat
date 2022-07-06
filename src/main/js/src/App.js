import TestScreen from "./pages/TestScreen";
import styled from "styled-components";
import LoginScreen from "./pages/LoginScreen";
import {AppContextProvider} from "./contexts/AppContext";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import InstructionScreen from "./pages/InstructionScreen";

function App() {
    return (
        <BrowserRouter>
            <AppContextProvider>
                <Routes>
                    <Route path={"/"}  element={<LoginScreen />} />
                    <Route path={"/instructions"}  element={<InstructionScreen />} />
                    <Route path={"/test"} element={<TestScreen />} />
                </Routes>
            </AppContextProvider>
        </BrowserRouter>
    );
};

export default App;
