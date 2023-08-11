# Use the official Nginx base image
FROM nginx:alpine

# Remove the default Nginx website
RUN rm -rf /usr/share/nginx/html/*

# Copy the built Vue.js files from the 'dist' directory to the Nginx web server directory
COPY dist/ /usr/share/nginx/html

# Expose the port that Nginx will listen on (default is 80)
EXPOSE 80

# Command to start the Nginx web server
CMD ["nginx", "-g", "daemon off;"]