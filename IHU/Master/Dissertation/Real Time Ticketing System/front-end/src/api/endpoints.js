const API_BASE_URL = process.env.REACT_APP_API_URL;

const endpoints = {
  REGISTER: `${API_BASE_URL}/auth/register`,
  LOGIN: `${API_BASE_URL}/auth/login`,
  CREATE_TICKET: `${API_BASE_URL}/create`,
};

export default endpoints;
