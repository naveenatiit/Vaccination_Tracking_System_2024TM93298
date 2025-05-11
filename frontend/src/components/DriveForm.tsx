import React, { useState } from 'react';
import axios from '../api/api';

const DriveForm = () => {
  const [form, setForm] = useState({
    vaccineName: '',
    driveDate: '',
    numberOfVaccinesAvailable: 0,
    applicableClasses: '',
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await axios.post('/api/vaccination-drives', form);
      alert('Drive created!');
    } catch (err: any) {
      alert('Error: ' + err.response?.data?.message || 'Could not create drive');
    }
  };

  return (
    <form className="container mt-4" onSubmit={handleSubmit}>
      <h3>Schedule Vaccination Drive</h3>
      <input className="form-control my-2" name="vaccineName" placeholder="Vaccine Name" onChange={handleChange} />
      <input className="form-control my-2" name="driveDate" type="date" onChange={handleChange} />
      <input className="form-control my-2" name="numberOfVaccinesAvailable" type="number" placeholder="Number of Doses" onChange={handleChange} />
      <input className="form-control my-2" name="applicableClasses" placeholder="Applicable Classes (e.g., 5,6,7)" onChange={handleChange} />
      <button className="btn btn-success">Create Drive</button>
    </form>
  );
};

export default DriveForm;
