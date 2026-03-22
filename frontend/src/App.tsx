import { useState } from 'react'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import TrackPage from "./pages/TrackParcel";
import HomePage from "./pages/Index";
import './App.css'

export default function App() {
    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/track/:trackingNumber" element={<TrackPage />} />
                </Routes>
            </BrowserRouter>
        </>
    );
}