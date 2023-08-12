if [[ "$OSTYPE" == "darwin"* ]]; then
    # macOS
    docker-compose up
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    # Linux
    docker-compose up
elif [[ "$OSTYPE" == "msys" || "$OSTYPE" == "cygwin" || "$OSTYPE" == "win32" ]]; then
    # Windows
    docker-compose up
else
    echo "Unsupported operating system."
fi

# if [[ "$OSTYPE" == "darwin"* ]]; then
#     # macOS
#     open "http://localhost:80"
# elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
#     # Linux
#     xdg-open "http://localhost:80"
# elif [[ "$OSTYPE" == "msys" || "$OSTYPE" == "cygwin" || "$OSTYPE" == "win32" ]]; then
#     # Windows
#     start "http://localhost:80"
# fi


# if [[ "$OSTYPE" == "darwin"* ]]; then
#     # macOS
#     open "http://localhost:8080/swagger-ui.html"
# elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
#     # Linux
#     xdg-open "http://localhost:8080/swagger-ui.html"
# elif [[ "$OSTYPE" == "msys" || "$OSTYPE" == "cygwin" || "$OSTYPE" == "win32" ]]; then
#     # Windows
#     start "http://localhost:8080/swagger-ui.html"
# fi