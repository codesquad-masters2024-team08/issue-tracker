import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState } from "react";
import Main from "./routes/Main";
import Login from "./routes/Login";
import NewIssue from "./routes/NewIssue";
import Labels from "./routes/Labels";
import Milestones from "./routes/Milestones";

function App() {
	//TODO 차후 context로 리펙토링
	const [darkMode, setDarkMode] = useState("");
	return (
		<div
			className={`${darkMode} w-screen h-screen bg-grayscale.100 flex justify-center items-center dark:bg-grayscale.900`}
		>
			<BrowserRouter>
				<Routes>
					<Route
						path="/"
						element={
							// <Login />
							<Main darkMode={darkMode} setDarkMode={setDarkMode} />
						}
					/>
					<Route
						path="/issue"
						element={<NewIssue darkMode={darkMode} setDarkMode={setDarkMode} />}
					/>
					<Route
						path="/labels"
						element={<Labels darkMode={darkMode} setDarkMode={setDarkMode} />}
					/>
					<Route
						path="/milestones"
						element={<Milestones darkMode={darkMode} setDarkMode={setDarkMode} />}
					/>
				</Routes>
			</BrowserRouter>
		</div>
	);
}

export default App;
