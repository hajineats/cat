import styled from "styled-components";
import BottomBar from "../components/BottomBar";
import TopBar from "../components/Topbar";
import {useLocation} from "react-router-dom";

const Container = styled.div`
  height: 100vh;
  display: grid;
  grid-template-rows: 70px 1fr 70px;
`

const Content = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`

const Instructions = styled.div`
  text-align: center;
  flex: 0 0 600px;
`


const EndScreen = () => {
    const {state} = useLocation();

    return (
        <Container>
            <TopBar/>
            <Content>
                <Instructions>
                    {state.msg}
                </Instructions>
            </Content>

            <BottomBar>
            </BottomBar>
        </Container>
    )
}

export default EndScreen