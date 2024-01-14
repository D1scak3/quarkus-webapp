async function fetchData() {
  const value = document.getElementById("number").value;

  try {
    const response = await fetch("http://localhost:8080/labseq/" + value, {
      method: "GET",
      mode: "cors",
      headers: {
        "Content-Type": "application/json",
        Accept: "*/*",
      },
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
}

async function showData() {
  try {
    const data = await fetchData();

    const preElement = document.getElementById("json-data");
    preElement.style.fontSize = "18px";
    preElement.innerHTML = JSON.stringify(data, null, 2);
  } catch (error) {
    console.error("Error displaying data:", error);
  }
}
