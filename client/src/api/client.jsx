import axios from "axios";

// Configured axios instance
const api = axios.create({
  baseURL: "/api",
  headers: { "Content-Type": "application/json" },
});

// GET /api/health — liveness check
export const getHealth = () => api.get("/health").then((r) => r.data);
