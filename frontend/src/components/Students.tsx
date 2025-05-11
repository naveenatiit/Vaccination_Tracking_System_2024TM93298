import React, { useState, useEffect } from 'react';
import { Container, Table, Button, Form, Modal } from 'react-bootstrap';
import api from '../api/api';
import { Student } from '../types/Students';

const Students: React.FC = () => {
  const [students, setStudents] = useState<Student[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [formData, setFormData] = useState<Student>({ name: '', grade: '', parentContact: '' });

  useEffect(() => {
    loadStudents();
  }, []);

  const loadStudents = async () => {
    const res = await api.get<Student[]>('/students/search');
    setStudents(res.data);
  };

  const handleSubmit = async () => {
    await api.post('/students/addOrEdit', formData);
    loadStudents();
    setShowModal(false);
  };

  const handleFileUpload = async (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      const formData = new FormData();
      formData.append('file', e.target.files[0]);
      await api.post('/students/bulkUpload', formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
      });
      loadStudents();
    }
  };

  return (
    <Container className="mt-4">
      <h2>Manage Students</h2>
      <Button variant="primary" onClick={() => setShowModal(true)}>Add Student</Button>
      <Form.Group className="mt-3">
        <Form.Label>Upload CSV for Bulk Import</Form.Label>
        <Form.Control type="file" onChange={handleFileUpload} />
      </Form.Group>

      <Table striped bordered hover className="mt-4">
        <thead>
          <tr>
            <th>Name</th>
            <th>Grade</th>
            <th>Parent Contact</th>
          </tr>
        </thead>
        <tbody>
          {students.map(student => (
            <tr key={student.id}>
              <td>{student.name}</td>
              <td>{student.grade}</td>
              <td>{student.parentContact}</td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Add Student</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group>
              <Form.Label>Name</Form.Label>
              <Form.Control type="text" value={formData.name} onChange={(e) => setFormData({...formData, name: e.target.value})} />
            </Form.Group>
            <Form.Group>
              <Form.Label>Grade</Form.Label>
              <Form.Control type="text" value={formData.grade} onChange={(e) => setFormData({...formData, grade: e.target.value})} />
            </Form.Group>
            <Form.Group>
              <Form.Label>Parent Contact</Form.Label>
              <Form.Control type="text" value={formData.parentContact} onChange={(e) => setFormData({...formData, parentContact: e.target.value})} />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>Close</Button>
          <Button variant="primary" onClick={handleSubmit}>Save</Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
};

export default Students;
