import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Dashboard from './pages/DashboardPage';
import StudentsPage from './components/Students';
import 'bootstrap/dist/css/bootstrap.min.css';
import DashboardPage from './pages/DashboardPage';
import LoginPage from './pages/LoginPage';


const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/dashboard" element={<DashboardPage />}/>
        <Route path="*" element={<LoginPage/>} />
        <Route path="/students" element={<StudentsPage />} />
      </Routes>
    </Router>
  );
}

export default App;