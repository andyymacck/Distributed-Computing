package main

import (
	"fmt"
	"net/http"
	"os"
)

func handleRequest(w http.ResponseWriter, r *http.Request) {
	path := r.URL.Path[1:]
	content, statusCode, err := readFile(path)
	if err != nil {
		http.Error(w, fmt.Sprintf("Error: %s", err), statusCode)
		return
	}

	w.WriteHeader(http.StatusOK)
	w.Write(content)
}

func readFile(path string) ([]byte, int, error) {
	print(path)
	filePath := "./files/" + path // Change the path accordingly
	content, err := os.ReadFile(filePath)
	if err != nil {
		if os.IsNotExist(err) {
			return nil, http.StatusNotFound, fmt.Errorf("404 Not Found: %s", path)
		}
		return nil, http.StatusInternalServerError, err
	}

	return content, http.StatusOK, nil
}

func startWebServer(webPort int) {
	http.HandleFunc("/", handleRequest)
	webAddr := fmt.Sprintf(":%d", webPort)
	fmt.Printf("Web server listening on port %d\n", webPort)
	http.ListenAndServe(webAddr, nil)
}

func main() {
	startWebServer(1235) // Change the web server port accordingly
}
