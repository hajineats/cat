import TestScreen from "./pages/TestScreen";
import styled from "styled-components";
import LoginScreen from "./pages/LoginScreen";
import {AppContextProvider} from "./contexts/AppContext";
import {BrowserRouter, Route, Routes} from "react-router-dom";

function App() {
    return (
        <BrowserRouter>
            <AppContextProvider>
                <Routes>
                    <Route path={"/test"} element={<TestScreen />} />
                    <Route path={"/"}  element={<LoginScreen />} />
                </Routes>
            </AppContextProvider>
        </BrowserRouter>
    );
};

export default App;
