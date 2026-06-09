import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
    plugins: [react()],
    server: {
        proxy: {
            // Forward any request starting with "/api" to the Spring Boot server.
            // This keeps the browser talking only to the Vite origin (localhost:5173),
            // so no CORS is involved — Vite relays the request to Spring server-side.
            '/api': {
                target: 'http://localhost:8080',
                changeOrigin: true,
            },
        },
    },
})
