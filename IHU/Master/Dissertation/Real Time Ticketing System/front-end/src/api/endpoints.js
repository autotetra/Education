const API_BASE_URL = process.env.REACT_APP_API_URL;

const endpoints = {
  REGISTER: `${API_BASE_URL}/auth/register`,
  LOGIN: `${API_BASE_URL}/auth/login`,
  CREATE_TICKET: `${API_BASE_URL}/tickets/create`,
  GET_TICKETS: `${API_BASE_URL}/tickets`,
  // Function-based endpoint for scalability
  GET_TICKET_BY_ID: (id) => `${API_BASE_URL}/tickets/${id}`,
  DELETE_TICKET: (id) => `${API_BASE_URL}/tickets/${id}`,
};

export default endpoints;
