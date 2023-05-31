package io.nishadc.automationtestingframework.testngcustomization.process;

import java.util.Map;

import org.apache.logging.log4j.Logger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import io.nishadc.automationtestingframework.logging.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLReportGenerator {
	private static final Logger logger = LoggerFactory.create(HTMLReportGenerator.class);

	private HTMLReportGenerator() {

	}

	public static void generateHTMLReport(String templateName, Map<String, Object> model, String reportName) {
		HTMLReportGenerator.logger.debug("Generating HTML Report. Template: {} Report Name: {}", templateName,
				reportName);
		HTMLReportGenerator.logger.debug("Generating HTML Report. Model: {}", model);
		Configuration configuration = new Configuration(new Version("2.3.23"));
		configuration.setDefaultEncoding("UTF-8");
		configuration.setClassForTemplateLoading(HTMLReportGenerator.class, "/reportTemplates");
		try {
			HTMLReportGenerator.writeToHtml(templateName, model, reportName, configuration);
		} catch (IOException e) {
			HTMLReportGenerator.logger.error("Failed to Generate HTML Report: {}",e.getMessage(), e);
		}
	}
	
	private static void writeToHtml(String templateName, Map<String, Object> model, String reportName,Configuration configuration) 
			throws IOException {
		File htmlReport = new File(String.format("./target/%s.html", reportName));
		Template template = configuration.getTemplate(String.format("%s.html", templateName));
		try (FileWriter out = new FileWriter(htmlReport)) {
			template.process(model, out);
			out.flush();
		} catch (TemplateException e) {
			HTMLReportGenerator.logger.error("Failed to Generate HTML Report: {}", e.getMessage(), e);
		}
	}
}
