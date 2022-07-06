import styled from "styled-components";


const Container = styled.div`
  display: flex;
  background-color: #D9D9D9;
  justify-content: flex-end;
  align-items: center;
  padding-right: 48px;
`

const BottomBar = ({children}) => {
    return (
        <Container>
            {children}
        </Container>
    )
}

export default BottomBar
