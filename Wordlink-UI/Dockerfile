# Stage 1: Build Angular app
FROM node:latest as builder
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build -- --prod

# Stage 2: Setup Apache web server
FROM nginx:alpine
COPY --from=builder /app/dist/wordlink-ui /usr/share/nginx/html