import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'

function App() {

  const result = "Тестовое приложение!";

  return (
    <>
      {/* <h1>Vite + React = {result} <img src={reactLogo}></img></h1> */}
      <div class="Main">
        <h1>{result}</h1>
        <img src={reactLogo} alt="123"></img>
        <p></p>
        <button class="btn">Кнопка</button>
    </div>
    </>
  )
}

export default App
