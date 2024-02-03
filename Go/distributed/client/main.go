package main

import (
	"fmt"
	"io"
	"net/http"
)

func sendRequest(url, proxyAddress string, proxyPort int) {
	proxyURL := fmt.Sprintf("http://%s:%d%s", proxyAddress, proxyPort, url)
	resp, err := http.Get(proxyURL)
	if err != nil {
		fmt.Println("Error sending request:", err)
		return
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		fmt.Printf("Received non-OK status code: %d\n", resp.StatusCode)

		return
	}

	body, err := io.ReadAll(resp.Body)
	if err != nil {
		fmt.Println("Error reading response:", err)
		return
	}

	fmt.Println(string(body))
}

func main() {
	proxyAddress := "localhost"
	proxyPort := 1234
	resourceURL := "/testfile.jpg"

	sendRequest(resourceURL, proxyAddress, proxyPort)
}
