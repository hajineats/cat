import styled from "styled-components";
import BottomBar from "../components/BottomBar";
import TopBar from "../components/Topbar";
import TestContent from "../components/TestContent";
import {useContext} from "react";
import {AppContext} from "../contexts/AppContext";
import Timer from "../components/widgets/Timer";
import {useLocation} from "react-router-dom";
import {confirmAlert} from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';


const Container = styled.div`
  height: 100vh;
  display: grid;
  grid-template-rows: 70px 1fr 70px;
`
const Button = styled.button`
  height: 32px;
  width: 124px;
  font-size: 16px;
  cursor: pointer;
`

const TestScreen = () => {
    const {state} = useLocation()
    const {handleTestSubmission} = useContext(AppContext)

    return (
        <Container>
            <TopBar><Timer timeRemainingInSeconds={state.timeRemainingInSeconds}/></TopBar>
            <TestContent>hello</TestContent>
            <BottomBar>
                <Button onClick={() => confirmAlert({
                    title: 'Confirm Test Submission',
                    message: 'Are you sure want to submit? You will not be able to change your answers after.',
                    buttons: [
                        {
                            label: 'Yes',
                            onClick: () => handleTestSubmission()
                        },
                        {
                            label: 'Cancel',
                        }
                    ]
                })}>
                    Submit Test
                </Button>
            </BottomBar>
        </Container>
    )
}

export default TestScreen