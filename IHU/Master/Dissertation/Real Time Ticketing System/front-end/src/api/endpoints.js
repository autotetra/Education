const API_BASE_URL = process.env.REACT_APP_API_URL;

const endpoints = {
  REGISTER: `${API_BASE_URL}/auth/register`,
  LOGIN: `${API_BASE_URL}/auth/login`,
};

export default endpoints;
