import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './pages/App';
import reportWebVitals from './reportWebVitals';
import {createUUID} from './utils/uuid';

createUUID();

ReactDOM.render(<App />, document.getElementById('root'));

reportWebVitals();
