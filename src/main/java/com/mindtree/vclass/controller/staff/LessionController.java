package com.mindtree.vclass.controller.staff;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindtree.vclass.exception.ServiceException;
import com.mindtree.vclass.model.Lession;
import com.mindtree.vclass.model.User;
import com.mindtree.vclass.service.LessionService;
import com.mindtree.vclass.service.Service;

/**
 * Servlet implementation class LessionController used to implement Lession
 * controller operations
 * 
 * @author D-HDKR
 * @version 1.0
 */
@WebServlet(urlPatterns = { 
		"/staff/lession", 
		"/staff/lession/show",
		"/staff/lession/approve",
		"/staff/lession/pending",
		"/staff/lession/create", 
		"/staff/lession/update",
		"/staff/lession/delete" 
})

public class LessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiate the controller
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public LessionController() {
		super();
	}

	/**
	 * Perform load balancing the request
	 * 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String route = request.getServletPath();

		try {

			switch (route) {

			case "/staff/lession":

				// Send request to index
				index(request, response);
				break;
				
			case "/staff/lession/show":
				
				// view the lession details
				show(request, response);
				break;
	
			case "/staff/lession/create":

				// Send request to create
				create(request, response);
				break;

			case "/staff/lession/update":

				// Send request to update
				update(request, response);
				break;

			case "/staff/lession/delete":

				// Send request to delete
				delete(request, response);
				break;

			default:

				// No route found
				response.sendError(404);
				break;
			}
		} catch (IOException e) {

			// Log the exception messages
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Delete a lession detials
	 * 
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			if (request.getMethod().equals("POST")) {

				String status = "";
				Service<Lession> service = new LessionService();

				// Get the slug of the lession to be delete
				String slug = request.getParameter("slug");

				if (service.isExists(slug) && service.delete(slug)) {

					// Return the success response
					status = "Lession details deleted !";
				} else {

					// Return the error message
					status = "Lession details delete failed !";
				}

				// Send status to the previous page
				response.sendRedirect(request.getContextPath() 
						+ "/staff/lession?status=" + status);

			} else {

				// If method is not post -> send method not supported
				response.sendError(405);
			}

		} catch (IOException | ServiceException e) {

			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Update the lession new details
	 * 
	 * @param request
	 * @param response
	 */
	private void update(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		try {

			Service<Lession> service = new LessionService();
			String slug = request.getParameter("slug");

			if (request.getMethod().equals("GET")) {

				// Get the lessionanme and set the lession details
				request.setAttribute("lession", service.read(slug));

				// Forward the request to eidt page
				getServletContext().getRequestDispatcher("/views/staff/lession/edit.jsp")
				.forward(request, response);

			} else if (request.getMethod().equals("POST")) {

				String status = "";
				Lession lession = null;

				if (service.isExists(slug)) {

					// Get lession new details
					lession = getLessionToUpdate(request, response);

					if (service.update(lession)) {

						// Return success response
						status = "Lession updated Successfully !";
					} else {

						// Return the error response
						status = "Lession updation failed !";
					}
				} else {

					// If lession already exists
					status = "Lession is not exists !";
				}

				// Redirect to response page
				response.sendRedirect(request.getContextPath() 
						+ "/staff/lession/update?slug=" + slug + "&status=" + status);
			}
		} catch (IOException | ServiceException | ServletException e) {

			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Get updated lession details
	 * 
	 * @param request
	 * @param response
	 * @return return new lession details to be update
	 */
	private Lession getLessionToUpdate(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		Lession lession = null;

		try {

			lession = new Lession();

			// Retrieve the lession updated value
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String slug = request.getParameter("slug");
			String video = request.getParameter("video");
			boolean isPublished = "on".equals(request.getParameter("publish"));

			// Retrieve lession previous details
			Lession lessionDetails = new LessionService().read(slug);

			// Set the lession new input
			lession.setTitle((title != null) ? title : lessionDetails.getTitle());
			lession.setDescription((description != null) 
					? description : lessionDetails.getDescription());
			lession.setSlug(lessionDetails.getSlug());
			lession.setVideo((video.isEmpty()) ? lessionDetails.getVideo() : video);
			lession.setApproved(lessionDetails.isApproved());
			lession.setPublished(isPublished);

		} catch (ServiceException e) {

			// Log the exception messages
			System.out.println(e.getMessage());
		}

		return lession;
	}

	/**
	 * Create the a new lession in DB
	 * 
	 * @param request
	 * @param response
	 */
	private void create(HttpServletRequest request, 			
			HttpServletResponse response) throws ServletException, IOException {

		try {

			if (request.getMethod().equals("GET")) {

				// Redirect to the create view page
				getServletContext().getRequestDispatcher("/views/staff/lession/create.jsp")
				.forward(request, response);

			} else if (request.getMethod().equals("POST")) {

				String status = "";

				Lession lession = new Lession();
				Service<Lession> service = new LessionService();
				String title = request.getParameter("title");

				// Retrieve & Set the lession input
				lession.setTitle(title);
				lession.setUser(((User) request.getSession().getAttribute("user")).getId());
				lession.setSlug(String.join("-", title.split(" ")).toLowerCase());
				lession.setDescription(request.getParameter("description"));
				lession.setPublished("on".equals(request.getParameter("publish")));
				lession.setVideo(request.getParameter("video"));
				lession.setApproved(false);

				if (!service.isExists(lession.getSlug())) {

					if (service.create(lession)) {

						// Return success response
						status = "Lession created Successfully !";
					} else {

						// Return the error response
						status = "Lession creation failed !";
					}
				} else {

					// If lession already exists
					status = "Lession already exists !";
				}

				// Send the request to previous page
				response.sendRedirect(request.getContextPath() 
						+ "/staff/lession/create?status=" + status);
			}
		} catch (IOException | ServiceException | ServletException e) {

			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Handle Index view of the lession
	 * 
	 * @param request
	 * @param response
	 */
	private void index(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			User user = (User) request.getSession().getAttribute("user");

			// Get all the lession details
			List<Lession> lessions = new LessionService().read()
					.stream().filter(lession -> (lession.getUser().getId() == user.getId()))
					.collect(Collectors.toList());

			request.setAttribute("lessions", lessions);

			// Send the request to staff->lession->index
			getServletContext().getRequestDispatcher("/views/staff/lession/index.jsp")
			.forward(request, response);

		} catch (ServletException | IOException | ServiceException e) {

			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Handle lession show page
	 * 
	 * @param request
	 * @param response
	 */
	private void show(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			String slug = request.getParameter("slug");
		
			// Get the lession details by slug
			Lession lession = new LessionService().read(slug);
			request.setAttribute("lession", lession);

			// Send the request to staff->lession->show
			getServletContext().getRequestDispatcher("/views/staff/lession/show.jsp")
			.forward(request, response);

		} catch (ServletException | IOException | ServiceException e) {

			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}
}
