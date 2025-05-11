import React, { useState } from 'react';
import axios from '../api/api';

const LoginForm = ({ onLogin }: { onLogin: () => void }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const res = await axios.post('/api/auth/login', { username, password });
      localStorage.setItem('token', res.data.token);
      onLogin();
    } catch (err) {
      alert('Invalid login');
    }
  };

  return (
    <form onSubmit={handleSubmit} className="container mt-5">
      <h3>Admin Login</h3>
      <input className="form-control my-2" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
      <input className="form-control my-2" type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button className="btn btn-primary" type="submit">Login</button>
    </form>
  );
};

export default LoginForm;
