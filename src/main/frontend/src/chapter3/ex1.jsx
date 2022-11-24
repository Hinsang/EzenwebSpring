const name = '소플'
const element = <h1>안녕 , {name} </h1>

React.render(element, document.querySelector("#root"))

const name = '소플';
const element = '<h1>안녕, '+name+'</h1>';
ReactDOM.render(element, document.querySelector('#root'));

// 함수
function formatName(user) {
  return user.firstName + ' ' + user.lastName
}

// 유저 객체
const user = { firstName : 'Inje', lastName : 'Lee' }
// html 구성
const element = (<h1>Hello, { formatName(user) }</h1>)
// 해당 id에 html 랜더링(뿌려주기/넣어주기/표시해주기)
ReactDOM.render(element, document.querySelector('#root'))

// ---------------- 활용 ---------------- //
function getGreeting(user) {
  if(user) {
    return <h1>Hello, { formatName(user) } ! </h1>
  }
  return <h1>Hello, Straner, </h1>
}

// ---------------- JSX 식 HTML ---------------- //
const element = <div><h1>안녕하세요</h1><h2>열심히 리액트를 공부해 봅시다!</h2></div>
const element = '<div><h1>안녕하세요</h1><h2>열심히 리액트를 공부해 봅시다!</h2></div>'
