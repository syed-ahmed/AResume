package com.thesyedahmed.aresume;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steakpizza on 8/30/2015.
 */
public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder> {
    Context mContext;
    public static class ProjectsViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView projectName;
        TextView projectDesc;
        ImageView projectPhoto;


        ProjectsViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView) itemView;
            projectName = (TextView) itemView.findViewById(R.id.project_name);
            projectDesc = (TextView) itemView.findViewById(R.id.project_desc);
            projectPhoto = (ImageView) itemView.findViewById(R.id.project_photo);
        }
    }

    public static List<ProjectsItem> projects;
    ProjectsAdapter(List<ProjectsItem> projects) {
        this.projects = projects;
    }

    public static List<ProjectsItem> getProjects() {
        return projects;
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    @Override
    public ProjectsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        ProjectsViewHolder pvh = new ProjectsViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ProjectsViewHolder projectsViewHolder, final int i) {
        CardView cv = projectsViewHolder.cv;
        projectsViewHolder.projectName.setText(projects.get(i).mName);
        projectsViewHolder.projectDesc.setText(projects.get(i).mDesc);
        projectsViewHolder.projectPhoto.setImageResource(projects.get(i).mPhotoID);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (projects.get(i).mID == 1) {
                        Intent intent = new Intent(v.getContext(), AboutActivity.class);
                        v.getContext().startActivity(intent);
                    }else if (projects.get(i).mID == 2) {
                    Intent intent = new Intent(v.getContext(), RocReadARActivity.class);
                    v.getContext().startActivity(intent);
                    } else if (projects.get(i).mID == 3) {
                        Intent intent = WebPageActivity.newIntent(v.getContext(),
                                Uri.parse(projects.get(i).mLink));
                        v.getContext().startActivity(intent);
                    } else if (projects.get(i).mID == 4) {
                        Intent intent = WebPageActivity.newIntent(v.getContext(),
                                Uri.parse(projects.get(i).mLink));
                        v.getContext().startActivity(intent);
                    } else if (projects.get(i).mID == 5) {
                        Intent intent = WebPageActivity.newIntent(v.getContext(),
                                Uri.parse(projects.get(i).mLink));
                        v.getContext().startActivity(intent);
                    } else if (projects.get(i).mID == 6) {
                        Intent intent = WebPageActivity.newIntent(v.getContext(),
                                Uri.parse(projects.get(i).mLink));
                        v.getContext().startActivity(intent);

                    }
                }
        });


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
