import React, { useEffect, useState } from 'react';
import axios from '../api/api';

const Dashboard = () => {
  const [metrics, setMetrics] = useState<any>(null);

  useEffect(() => {
    axios.get('/api/dashboard')
      .then(res => setMetrics(res.data))
      .catch(() => alert('Failed to load dashboard'));
  }, []);

  if (!metrics) return <div>Loading...</div>;

  return (
    <div className="container mt-4">
      <h2>Dashboard</h2>
      <p>Total Students: {metrics.totalStudents}</p>
      <p>Vaccinated: {metrics.vaccinatedStudents} ({metrics.vaccinatedPercentage}%)</p>

      <h4 className="mt-4">Upcoming Drives (Next 30 Days)</h4>
      {metrics.upcomingDrives.length === 0 ? (
        <p>No upcoming drives</p>
      ) : (
        <ul>
          {metrics.upcomingDrives.map((drive: any) => (
            <li key={drive.id}>{drive.vaccineName} on {drive.driveDate}</li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Dashboard;
