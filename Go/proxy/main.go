package main

import (
	"fmt"
	"io"
	"net"
)

func handleClient(client net.Conn, webServerAddr string) {
	// Connect to the web server
	webServer, err := net.Dial("tcp", webServerAddr)
	if err != nil {
		fmt.Println("Error connecting to web server:", err)
		client.Close()
		return
	}
	defer webServer.Close()

	// Use goroutines for bidirectional copying
	go func() {
		_, err := io.Copy(webServer, client)
		if err != nil {
			fmt.Println("Error copying data to web server:", err)
		}
	}()

	_, err = io.Copy(client, webServer)
	if err != nil {
		fmt.Println("Error copying data to client:", err)
	}

	// If you need to send specific HTTP status codes, you can use the http.Error function
	// Example:
	// http.Error(client, "Internal Server Error", http.StatusInternalServerError)
}

func startProxyServer(proxyPort int, webServerAddr string) {
	// Start proxy server and listen for incoming client connections
	proxyAddr := fmt.Sprintf(":%d", proxyPort)
	proxyListener, err := net.Listen("tcp", proxyAddr)
	if err != nil {
		fmt.Println("Error starting proxy server:", err)
		return
	}
	defer proxyListener.Close()

	fmt.Printf("Proxy server listening on port %d\n", proxyPort)

	for {
		// Accept incoming client connection
		client, err := proxyListener.Accept()
		if err != nil {
			fmt.Println("Error accepting client connection:", err)
			continue
		}

		// Handle the client connection in a separate goroutine
		go handleClient(client, webServerAddr)
	}
}

func main() {
	webServerAddr := "localhost:1235" // Change the web server address and port accordingly
	startProxyServer(1234, webServerAddr)
}
