package com.mindtree.vclass.controller.admin;

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
		"/admin/lession", 
		"/admin/lession/show",
		"/admin/lession/approve",
		"/admin/lession/pending",
		"/admin/lession/create", 
		"/admin/lession/update",
		"/admin/lession/delete" 
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

			case "/admin/lession":

				// Send request to index
				index(request, response);
				break;
				
			case "/admin/lession/show":
				
				// view the lession details
				show(request, response);
				break;
				
			case "/admin/lession/pending":

				// Send request to pending lession
				getPendingLessions(request, response);
				break;
				
				
			case "/admin/lession/approve":

				// Approve the lession by Admin
				approveLession(request, response);
				break;

			case "/admin/lession/create":

				// Send request to create
				create(request, response);
				break;

			case "/admin/lession/update":

				// Send request to update
				update(request, response);
				break;

			case "/admin/lession/delete":

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
						+ "/admin/lession?status=" + status);

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
				getServletContext().getRequestDispatcher("/views/admin/lession/edit.jsp")
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
						+ "/admin/lession/update?slug=" + slug + "&status=" + status);
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
				getServletContext().getRequestDispatcher("/views/admin/lession/create.jsp")
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
				lession.setApproved(true);

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
						+ "/admin/lession/create?status=" + status);
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

			// Get all the lession details
			List<Lession> lessions = new LessionService().read();

			request.setAttribute("lessions", lessions);
			response.setHeader("location", "All");

			// Send the request to admin->lession->index
			getServletContext().getRequestDispatcher("/views/admin/lession/index.jsp")
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

			// Send the request to admin->lession->show
			getServletContext().getRequestDispatcher("/views/admin/lession/show.jsp")
			.forward(request, response);

		} catch (ServletException | IOException | ServiceException e) {

			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * Handle pending lession view
	 * 
	 * @param request
	 * @param response
	 */
	private void getPendingLessions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			// Get all the lession details
			List<Lession> pendingLessions = new LessionService().read()
					.stream().filter(lession -> (!lession.isApproved()))
					.collect(Collectors.toList());

			request.setAttribute("lessions", pendingLessions);
			response.setHeader("location", "Pending");

			// Send the request to admin->lession->pending
			getServletContext().getRequestDispatcher("/views/admin/lession/index.jsp")
			.forward(request, response);

		} catch (ServletException | IOException | ServiceException e) {

			// Log the exception messages
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * Approve the lession by Admin
	 * 
	 * @param request
	 * @param response
	 */
	private void approveLession(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		try {

			if (request.getMethod().equals("POST")) {

				String status = "";				
				Service<Lession> service = new LessionService();
				String slug = request.getParameter("slug");

				if (service.isExists(slug)) {

					Lession lession = service.read(slug);
					
					// Approve the lession
					lession.setApproved(true);
					
					if (service.update(lession)) {

						// Return success response
						status = "Lession Approved !";
					} else {

						// Return the error response
						status = "Lession approval failed !";
					}
				} else {

					// If lession already exists
					status = "Lession is not exists !";
				}

				// Redirect to response page
				response.sendRedirect(request.getContextPath() 
						+ "/admin/lession/pending?slug=" + slug + "&status=" + status);
			} else {
				
				// If the reques is not in post method
				response.sendError(405, "Requested method not allowed !");
			}
		} catch (IOException | ServiceException | NullPointerException e) {

			// Log the exception messages
			System.out.println(e.getMessage());
		}
	}
}
