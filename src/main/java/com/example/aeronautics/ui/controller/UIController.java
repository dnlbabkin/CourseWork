package com.example.aeronautics.ui.controller;

import com.example.aeronautics.DAO.AeronauticsModel;
import com.example.aeronautics.configuration.AeronauticsConfiguration;
import com.example.aeronautics.configuration.properties.AeronauticsProperties;
import com.example.aeronautics.configuration.properties.FileProperties;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import org.springframework.http.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


@Component
@RequiredArgsConstructor
@Slf4j
public class UIController {

    private final AeronauticsConfiguration template;
    private final AeronauticsProperties properties;
    private final FileProperties file;

    @FXML
    public TextField createId;

    @FXML
    public TextField createName;

    @FXML
    public TextField createType;

    @FXML
    public TextField createSpeed;

    @FXML
    public Button create;

    @FXML
    public TextField updateId;

    @FXML
    public TextField updateName;

    @FXML
    public TextField updateType;

    @FXML
    public TextField updateSpeed;

    @FXML
    public Button update;

    @FXML
    public TextField getText;

    @FXML
    public Button get;

    @FXML
    public TextField deleteText;

    @FXML
    public Button delete;

    @FXML
    public void initialize() {
        this.create.setOnAction(actionEvent -> createObject());
        this.get.setOnAction(actionEvent -> getAndSaveObject());
        this.update.setOnAction(actionEvent -> updateObject());
        this.delete.setOnAction(actionEvent -> deleteObject());
    }

    private void deleteObject() {
        template.getTemplate(new RestTemplateBuilder()).delete(properties.getDelete() + deleteText.getText());
        try(PrintWriter writer = new PrintWriter(file.getResponse())){
            writer.print("");

            log.info("Удаление записи с файла проищошло успешно");
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось почистить файл " + e);
        }
    }

    private void updateObject() {
        AeronauticsModel model = new AeronauticsModel();
        model.setAeronauticsId(Long.valueOf(updateId.getText()));
        model.setAircraftName(updateName.getText());
        model.setAircraftType(updateType.getText());
        model.setDevelopedSpeed(Integer.valueOf(updateSpeed.getText()));

        makeEntity(model);
        template.getTemplate(new RestTemplateBuilder()).exchange(properties.getUpdate() + getText.getText(),
                HttpMethod.PUT, makeEntity(model), AeronauticsModel.class);

        log.info("Редактирование объекта произошло успешно");
    }

    private void getAndSaveObject() {
        ResponseEntity<AeronauticsModel> response = createGetRequest();

        writeResponseToFile(response);
    }

    private ResponseEntity<AeronauticsModel> createGetRequest() {
        return template.getTemplate(new RestTemplateBuilder())
                .getForEntity(properties.getGet() + getText.getText(), AeronauticsModel.class);
    }

    private void writeResponseToFile(ResponseEntity<AeronauticsModel> response) {
        try(FileWriter writer = new FileWriter(file.getResponse())){
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(response.getBody());
            writer.write(xml);

            log.info("Запись в файл завершена успешно");
        } catch (IOException e) {
            System.out.println("Не удалось произвести запись " + e);
        }
    }

    private void createObject() {
        AeronauticsModel model = new AeronauticsModel();
        model.setAeronauticsId(Long.valueOf(createId.getText()));
        model.setAircraftName(createName.getText());
        model.setAircraftType(createType.getText());
        model.setDevelopedSpeed(Integer.valueOf(createSpeed.getText()));

        HttpEntity<AeronauticsModel> request = makeEntity(model);
        createRequest(request);
    }

    private void createRequest(HttpEntity<AeronauticsModel> request) {
        template.getTemplate(new RestTemplateBuilder())
                .postForObject(properties.getCreate(), request, AeronauticsModel.class);
    }

    private HttpEntity<AeronauticsModel> makeEntity(AeronauticsModel model) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);

        return new HttpEntity<>(model, headers);
    }
}
