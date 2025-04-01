document.addEventListener('DOMContentLoaded', () => {
    const navLinks = document.querySelectorAll('.sidebar-nav a');
    const sections = document.querySelectorAll('.content-section');
    const forms = document.querySelectorAll('.dss-form'); // Get all forms

    // --- Navigation Logic ---
    navLinks.forEach(link => {
        link.addEventListener('click', (event) => {
            event.preventDefault();
            const targetId = link.getAttribute('href')?.substring(1); // Get ID without '#' safely

            if (!targetId) return; // Exit if href is missing or invalid

            // Update active link
            navLinks.forEach(l => l.classList.remove('active'));
            link.classList.add('active');

            // Hide all sections
            sections.forEach(section => section.classList.add('hidden'));

            // Show target section
            const targetSection = document.getElementById(targetId);
            if (targetSection) {
                targetSection.classList.remove('hidden');
                // Update main header title (optional)
                const linkText = link.textContent.trim() || link.querySelector('i')?.title || 'Section'; // Get text or icon title
                 document.querySelector('.main-header h1').textContent = linkText + ' Overview';
            } else {
                 console.error("Target section not found:", targetId);
                 // Show dashboard as fallback maybe?
                 document.getElementById('dashboard')?.classList.remove('hidden');
                 document.querySelector('.sidebar-nav a[href="#dashboard"]')?.classList.add('active');
            }
        });
    });

    // --- Form Submission Handling (Simulation) ---
    forms.forEach(form => {
        form.addEventListener('submit', (event) => {
            event.preventDefault(); // Prevent actual form submission
            const formId = form.id;

            // Find the button within the form
            const button = form.querySelector('button[type="submit"]');
            if (!button) return; // Should not happen if HTML is correct

            // Handle based on form ID
            switch (formId) {
                case 'pricing-form':
                    simulatePricingAnalysis(form, button);
                    break;
                case 'forecast-form':
                    simulateForecastGeneration(form, button);
                    break;
                case 'channel-form':
                    simulateChannelAnalysis(form, button);
                    break;
                 case 'reviews-form': // ADDED CASE
                    simulateReviewAnalysis(form, button);
                    break;
                case 'report-form':
                    simulateReportGeneration(form, button);
                    break;
                default:
                    console.warn('Unhandled form:', formId);
            }
        });
    });

    // --- Simulation Functions ---

    function showLoading(button, resultsContainer) {
        // Disable button and show loading state
        button.disabled = true;
        button.classList.add('loading'); // Add loading class for styling
        const originalTextSpan = button.querySelector('.original-text');
        const loadingTextSpan = button.querySelector('.loading-text');
        const icon = button.querySelector('i');

        if (originalTextSpan) originalTextSpan.style.display = 'none';
        if (loadingTextSpan) loadingTextSpan.style.display = 'inline';
        // Icon spinning is handled by CSS via .loading class

        // Show spinner in results area and ensure container is visible
        if (resultsContainer) {
            const resultsContent = resultsContainer.querySelector('.results-content');
            const spinner = resultsContainer.querySelector('.loading-spinner');
            if (resultsContent) resultsContent.classList.add('loading'); // Dim content
            if (spinner) spinner.classList.remove('hidden');
            resultsContainer.classList.remove('hidden'); // Make sure container is visible
        }
    }

    function hideLoading(button, resultsContainer) {
         // Re-enable button and restore original state
         button.disabled = false;
         button.classList.remove('loading'); // Remove loading class
         const originalTextSpan = button.querySelector('.original-text');
         const loadingTextSpan = button.querySelector('.loading-text');

         if (originalTextSpan) originalTextSpan.style.display = 'inline';
         if (loadingTextSpan) loadingTextSpan.style.display = 'none';
         // Icon spinning stops automatically when .loading class is removed

        // Hide spinner in results area
         if (resultsContainer) {
            const resultsContent = resultsContainer.querySelector('.results-content');
            const spinner = resultsContainer.querySelector('.loading-spinner');
            if (resultsContent) resultsContent.classList.remove('loading'); // Undim content
            if (spinner) spinner.classList.add('hidden');
        }
    }

    function simulatePricingAnalysis(form, button) {
        const resultsContainer = document.getElementById('pricing-results');
        // Get input values
        const startDate = form.querySelector('#pricing-start-date').value;
        const endDate = form.querySelector('#pricing-end-date').value;
        const roomType = form.querySelector('#room-type-filter').value;

        // Basic validation
        if (!startDate || !endDate) {
            alert('Please select both start and end dates.');
            resultsContainer?.classList.add('hidden'); // Hide results if invalid
            return; // Exit before showing loading
        }

        showLoading(button, resultsContainer);

        setTimeout(() => { // Simulate network delay/processing
            if (!resultsContainer) return; // Check if container exists

            // Update results content (SIMULATED DATA)
            document.getElementById('pricing-result-dates').textContent = `${startDate} to ${endDate}`;

            // Slightly randomize results for demo effect
            const randomFactor = Math.random();
            let demandLevel, demandFactors, compRates, recommendation;

            if (randomFactor < 0.3) {
                demandLevel = 'Low'; demandFactors = 'Off-season, low booking pace';
                compRates = 'Hotel A: $115 | Hotel B: $125 | Hotel C: $110';
                recommendation = `${roomType === 'all' || roomType === 'standard' ? 'Standard: <strong>$120 - $130</strong><br>' : ''}
                                ${roomType === 'all' || roomType === 'deluxe' ? 'Deluxe: <strong>$150 - $165</strong><br>' : ''}
                                ${roomType === 'all' || roomType === 'suite' ? 'Suite: <strong>$190 - $210</strong><br>' : ''}`;
            } else if (randomFactor < 0.7) {
                demandLevel = 'Medium'; demandFactors = 'Shoulder season, average pace';
                compRates = 'Hotel A: $145 | Hotel B: $155 | Hotel C: $140';
                 recommendation = `${roomType === 'all' || roomType === 'standard' ? 'Standard: <strong>$150 - $165</strong><br>' : ''}
                                ${roomType === 'all' || roomType === 'deluxe' ? 'Deluxe: <strong>$180 - $195</strong><br>' : ''}
                                ${roomType === 'all' || roomType === 'suite' ? 'Suite: <strong>$230 - $250</strong><br>' : ''}`;
            } else {
                 demandLevel = 'High'; demandFactors = 'Peak season, event nearby, high pace';
                 compRates = 'Hotel A: $195 | Hotel B: $210 | Hotel C: $190';
                 recommendation = `${roomType === 'all' || roomType === 'standard' ? 'Standard: <strong>$200 - $220</strong><br>' : ''}
                                ${roomType === 'all' || roomType === 'deluxe' ? 'Deluxe: <strong>$240 - $260</strong><br>' : ''}
                                ${roomType === 'all' || roomType === 'suite' ? 'Suite: <strong>$300 - $330</strong><br>' : ''}`;
            }

            const demandEl = document.getElementById('demand-level');
            if(demandEl) {
                demandEl.textContent = demandLevel;
                demandEl.className = `highlight ${demandLevel.toLowerCase()}`; // Add class for styling
            }
            const demandFactorsEl = document.getElementById('demand-factors');
            if(demandFactorsEl) demandFactorsEl.textContent = demandFactors;

            const compRatesEl = document.getElementById('competitor-rates');
            if(compRatesEl) compRatesEl.textContent = compRates;

            const rateRecEl = document.getElementById('rate-recommendation');
            if(rateRecEl) rateRecEl.innerHTML = recommendation + "Consider dynamic adjustments based on pickup."; // Use innerHTML for bold tags

            // Add Discount Recommendation Logic (Simulated)
            const discountEl = document.getElementById('discount-recommendation');
            if(discountEl) {
                if (demandLevel === 'Low') {
                    discountEl.innerHTML = `Consider targeted discounts (e.g., 10-15%) for loyalty members or specific segments. Promote weekend packages. Low occupancy detected near ${startDate}.`;
                } else if (demandLevel === 'Medium' && Math.random() > 0.6) { // Occasionally suggest mid-level discounts
                     discountEl.innerHTML = `Opportunity for advanced purchase discount (5-10%) to secure base business. Check competitor promotions.`;
                } else {
                    discountEl.innerHTML = `High demand period. Focus on maximizing rate rather than discounts. Ensure BAR pricing is optimal.`;
                }
            }

            hideLoading(button, resultsContainer);

        }, 1500); // 1.5 second delay
    }

    function simulateForecastGeneration(form, button) {
        const resultsContainer = document.getElementById('forecast-results');
        if (!resultsContainer) return;

        const range = form.querySelector('#forecast-range').value;
        const rangeText = form.querySelector('#forecast-range option:checked').text;

        showLoading(button, resultsContainer);

        setTimeout(() => {
            document.getElementById('forecast-result-horizon').textContent = rangeText;
            const tableBody = document.getElementById('forecast-table')?.querySelector('tbody');
            if (!tableBody) return; // Exit if table body not found

            tableBody.innerHTML = ''; // Clear previous results

            // Generate dummy data
            const days = parseInt(range);
            const today = new Date();

            for (let i = 0; i < days; i++) {
                const date = new Date(today);
                date.setDate(today.getDate() + i);
                const dateString = date.toISOString().split('T')[0];
                const dayOfWeek = date.toLocaleDateString('en-US', { weekday: 'short' });

                const occupancy = Math.floor(Math.random() * 40) + 50; // 50-90% range
                const arrivals = Math.floor(Math.random() * 30) + 10;
                const departures = Math.floor(Math.random() * 30) + 10;
                let notes = '';
                if (occupancy > 88) notes = '<span class="alert-note">High Sell-Out Risk!</span>';
                else if (occupancy > 80 && (dayOfWeek === 'Sat' || dayOfWeek === 'Fri')) notes = '<span class="alert-note">High Weekend Demand</span>';
                else if (i > 5 && i < days - 2 && Math.random() > 0.85) notes = 'Local Event Boost';
                else if (dayOfWeek === 'Mon' || dayOfWeek === 'Tue') notes = 'Midweek Trough';


                const row = `<tr>
                                <td>${dateString}</td>
                                <td>${dayOfWeek}</td>
                                <td>${occupancy}%</td>
                                <td>${arrivals}</td>
                                <td>${departures}</td>
                                <td>${notes}</td>
                             </tr>`;
                tableBody.innerHTML += row;
            }

            hideLoading(button, resultsContainer);
        }, 2000); // 2 second delay
    }

     function simulateChannelAnalysis(form, button) {
        const resultsContainer = document.getElementById('channel-results');
        if (!resultsContainer) return;

        const period = form.querySelector('#channel-period').value;
         if (!period) {
            alert('Please select a period.');
            resultsContainer.classList.add('hidden');
            return; // Exit before loading
        }
        showLoading(button, resultsContainer);

        setTimeout(() => {
            document.getElementById('channel-result-period').textContent = period;

            // Dummy data based loosely on period (just to show change)
            const month = new Date(period + '-01').getMonth(); // 0-11
            const baseBookings = 500;
            const baseRevenue = 80000;

            // Simulate seasonal variation
            const seasonalFactor = 1 + (Math.sin((month - 3) * (Math.PI / 6)) * 0.3); // Peaks around summer

            const webBookings = Math.round(baseBookings * 0.4 * seasonalFactor);
            const webRevenue = Math.round(baseRevenue * 0.45 * seasonalFactor);
            const otaBookings = Math.round(baseBookings * 0.5 * seasonalFactor);
            const otaRevenue = Math.round(baseRevenue * 0.45 * seasonalFactor);
            const phoneBookings = Math.round(baseBookings * 0.1 * seasonalFactor);
            const phoneRevenue = Math.round(baseRevenue * 0.1 * seasonalFactor);

            document.getElementById('ch-web-bookings').textContent = webBookings;
            document.getElementById('ch-web-revenue').textContent = '$' + webRevenue.toLocaleString();
            document.getElementById('ch-web-conv').textContent = (2.5 + Math.random()).toFixed(1) + '%';

            document.getElementById('ch-ota-bookings').textContent = otaBookings;
            document.getElementById('ch-ota-revenue').textContent = '$' + otaRevenue.toLocaleString();
            document.getElementById('ch-ota-comm').textContent = (15 + Math.random() * 3).toFixed(1) + '%';

            document.getElementById('ch-phone-bookings').textContent = phoneBookings;
            document.getElementById('ch-phone-revenue').textContent = '$' + phoneRevenue.toLocaleString();

            // Update chart placeholder text or potentially use a JS charting library here
            const chartImg = resultsContainer.querySelector('.chart-placeholder img');
            if (chartImg) chartImg.alt = `Revenue Share for ${period}`;


            hideLoading(button, resultsContainer);
        }, 1200); // 1.2 second delay
     }

     function simulateReviewAnalysis(form, button) { // ADDED FUNCTION
        const resultsContainer = document.getElementById('reviews-results');
         if (!resultsContainer) return;

        const startDate = form.querySelector('#reviews-start-date').value;
        const endDate = form.querySelector('#reviews-end-date').value;
        const source = form.querySelector('#review-source').value;

        // Use current month if dates not selected for demo purposes
        const periodText = (startDate && endDate) ? `${startDate} to ${endDate}` : `Current Period (Simulated)`;
        const sourceText = form.querySelector('#review-source option:checked').text;

        showLoading(button, resultsContainer);

        setTimeout(() => {
            document.getElementById('reviews-result-period').textContent = `${periodText}, Source: ${sourceText}`;

            // Simulate KPI data
            const avgRatingEl = document.getElementById('avg-rating');
            if (avgRatingEl) avgRatingEl.innerHTML = (4.0 + Math.random() * 0.8).toFixed(1) + '<span style="font-size:0.5em">/5</span>';

            const positivePerc = Math.floor(Math.random() * 20) + 75; // 75-95%
            const posSentEl = document.getElementById('positive-sentiment');
            if (posSentEl) posSentEl.textContent = positivePerc + '%';

            const negSentEl = document.getElementById('negative-sentiment');
             if (negSentEl) negSentEl.textContent = (100 - positivePerc) + '%';

            // Simulate Themes
            const positiveThemesList = document.getElementById('positive-themes');
            if (positiveThemesList) {
                positiveThemesList.innerHTML = `<li>Location (${Math.floor(Math.random()*20)+10})</li>
                                                <li>Staff Friendliness (${Math.floor(Math.random()*15)+8})</li>
                                                <li>Cleanliness (${Math.floor(Math.random()*10)+5})</li>
                                                <li>Comfortable Bed (${Math.floor(Math.random()*8)+3})</li>`; // Numbers are fake mention counts
            }

            const negativeThemesList = document.getElementById('negative-themes');
            if(negativeThemesList) {
                 negativeThemesList.innerHTML = `<li>Wi-Fi Speed (${Math.floor(Math.random()*8)+4})</li>
                                                 <li>Breakfast Options (${Math.floor(Math.random()*6)+3})</li>
                                                 <li>Noise (${Math.floor(Math.random()*5)+2})</li>
                                                 <li>Value for Money (${Math.floor(Math.random()*4)+1})</li>`;
            }
            // Update snippets (or keep static, as they are hardcoded in HTML for now)
            // You could add logic here to slightly vary the snippets if needed

            hideLoading(button, resultsContainer);
        }, 1600); // 1.6 second delay
    }


     function simulateReportGeneration(form, button) {
        const resultsContainer = document.getElementById('report-output');
        if (!resultsContainer) return;

        const reportTypeValue = form.querySelector('#report-type').value; // Get the value
        const reportTypeText = form.querySelector('#report-type option:checked').text; // Get the display text
        const reportPeriod = form.querySelector('#report-period').value;

        if (!reportPeriod) {
            alert('Please select a report period.');
            resultsContainer.classList.add('hidden');
            return; // Exit before loading
        }
        showLoading(button, resultsContainer);

        setTimeout(() => {
            document.getElementById('report-output-title').textContent = reportTypeText; // Use selected text for title
            document.getElementById('report-output-period').textContent = reportPeriod;

            // Update preview content based on report type
            const previewArea = resultsContainer.querySelector('.report-preview p');
            let previewContent = '';

            switch (reportTypeValue) {
                case 'perf':
                    previewContent = `Simulated **Monthly Performance Summary** for ${reportPeriod}. Includes key metrics like Occupancy %, ADR, RevPAR, Total Revenue, comparison vs. budget/last year. Charts visualizing trends would be here.`;
                    break;
                case 'pace':
                    previewContent = `Simulated **Booking Pace Report** for future periods relative to ${reportPeriod}. Shows bookings on hand for the next 30/60/90 days compared to the same time last year and forecast targets. Crucial for identifying booking trends and adjusting strategy. Includes pickup analysis.`;
                    break;
                case 'channel':
                    previewContent = `Simulated **Booking Channel Analysis** for ${reportPeriod}. Breaks down bookings, revenue, room nights, and ADR by channel (Direct Website, OTA, GDS, Phone, Walk-in). Includes cost of acquisition (commission) analysis where applicable to show channel profitability.`;
                    break;
                case 'los':
                    previewContent = `Simulated **Length of Stay (LOS) Analysis** for ${reportPeriod}. Details guest stay patterns. Shows number of bookings, room nights, and revenue generated for each LOS category (1 night, 2 nights, 3-4 nights, 5+ nights). Helps in tailoring packages and rate strategies.`;
                    break;
                case 'cancel':
                    previewContent = `Simulated **Cancellation & No-Show Report** for ${reportPeriod}. Tracks the number and percentage of cancellations and no-shows. May include cancellation reasons (if captured), lead time for cancellations, and impact on revenue. Used for refining overbooking levels.`;
                    break;
                case 'segment':
                    previewContent = `Simulated **Guest Segment Analysis** for ${reportPeriod}. Analyzes performance based on market segments (e.g., Transient, Corporate, Group, Leisure). Shows room nights, ADR, and revenue contributed by each segment, helping target marketing efforts.`;
                    break;
                case 'comp':
                    previewContent = `Simulated **Competitor Set Analysis** for ${reportPeriod}. Compares your hotel's performance (Occupancy, ADR, RevPAR - if data available via STR or similar) against your defined competitors. Includes rate shopping data summaries showing your position relative to competitors on key dates.`;
                    break;
                default:
                    previewContent = 'Simulated preview content for the selected report. Details would vary based on the report type.';
            }
            if (previewArea) previewArea.innerHTML = previewContent.replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>'); // Basic bold formatting

            // --- Update Download Link Simulations ---
            const pdfLink = document.getElementById('download-pdf-link');
            const csvLink = document.getElementById('download-csv-link');

            // Modify the onclick alerts slightly (still not real downloads)
             if (pdfLink) {
                pdfLink.onclick = (e) => {
                    e.preventDefault(); // Prevent '#' navigation
                    alert(`Simulating PDF download for: ${reportTypeText} (${reportPeriod})...`);
                };
             }
             if (csvLink) {
                csvLink.onclick = (e) => {
                    e.preventDefault(); // Prevent '#' navigation
                    alert(`Simulating CSV download for: ${reportTypeText} (${reportPeriod})...`);
                };
             }

             hideLoading(button, resultsContainer);
         }, 1800); // 1.8 second delay
     }

    // --- Initial State ---
    function initializeDashboard() {
         // Ensure only the dashboard is visible on load
        sections.forEach(section => {
            if (section.id !== 'dashboard') {
                section.classList.add('hidden');
            } else {
                section.classList.remove('hidden');
            }
        });
        // Set dashboard link as active
         navLinks.forEach(l => l.classList.remove('active'));
         document.querySelector('.sidebar-nav a[href="#dashboard"]')?.classList.add('active');
         // Set initial header title
         document.querySelector('.main-header h1').textContent = 'Dashboard Overview';
    }

    initializeDashboard(); // Call initialization function

});