package com.kodilla.outdoor.equiprent.frontendvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import org.springframework.web.client.RestTemplate;

@Route("dashboard")
public class DashboardView extends VerticalLayout {
    private final RestTemplate restTemplate = new RestTemplate();
    // Labels for dashboard summary
    private final Span currentRentalsLabel = new Span("Aktualne wypożyczenia: X");
    private final Span availableEquipmentLabel = new Span("Dostępny sprzęt: Y");
    private final Span reportsLabel = new Span("Raporty: Z");
    // Weather elements
    private final TextField locationField = new TextField("Wprowadź lokalizację");
    private final Span temperatureLabel = new Span("Temperatura: -");
    private final Span humidityLabel = new Span("Wilgotność: -");
    private final Span conditionsLabel = new Span("Warunki: -");

    public DashboardView() {
        // Create dashboard layout
        createDashboardLayout();
        // Create weather layout
        createWeatherLayout();
    }

    private void createDashboardLayout() {
        // Create a header for the dashboard section
        H1 dashboardHeader = new H1("Dashboard");
        // Create a vertical layout for the dashboard section
        VerticalLayout dashboardLayout = new VerticalLayout();
        dashboardLayout.add(dashboardHeader, currentRentalsLabel, availableEquipmentLabel, reportsLabel);
        // Add the dashboard layout to the main view
        add(dashboardLayout);
        // Fetch and update data for dashboard
        updateDashboardData();
    }

    private void createWeatherLayout() {
        // Create a header for the weather section
        H1 weatherHeader = new H1("Pogoda");
        // Create a button for fetching weather data
        Button fetchWeatherButton = new Button("Pobierz prognozę", event -> fetchWeather());
        // Create a layout for the weather section
        VerticalLayout weatherLayout = new VerticalLayout();
        weatherLayout.add(weatherHeader, locationField, fetchWeatherButton, temperatureLabel, humidityLabel, conditionsLabel);
        // Add the weather layout to the main view
        add(weatherLayout);
    }

    private void updateDashboardData() {
        // Example data fetching (replace with real API calls)
        currentRentalsLabel.setText("Aktualne wypożyczenia: 5");
        availableEquipmentLabel.setText("Dostępny sprzęt: 10");
        reportsLabel.setText("Raporty: 2");
    }

    private void fetchWeather() {
        String location = locationField.getValue();
        if (location.isEmpty()) {
            Notification.show("Uzupełnij lokalizację.");
            return;
        }

        // Example weather data fetching (replace with real API call)
        // String weatherApiUrl = "https://api.weather.com/forecast?location=" + location;
        // WeatherResponse response = restTemplate.getForObject(weatherApiUrl, WeatherResponse.class);

        // Mock weather data for demonstration
        temperatureLabel.setText("Temperatura: 15°C");
        humidityLabel.setText("Wilgotność: 70%");
        conditionsLabel.setText("Warunki: Słonecznie");

        Notification.show("Pobrano prognozę dla: " + location);
    }
}